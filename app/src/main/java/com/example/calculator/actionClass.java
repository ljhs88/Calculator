package com.example.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class actionClass {

    ArrayList<String> first;

    Stack<String> second;

    private StringBuffer sb;

    private int k = 0;

    private String s;

    private int point;

    private int n;

    private int end;

    public actionClass(StringBuffer sb) {
        this.sb = sb;
    }

    public String getAnswer() {
        first = new ArrayList<>();
        second = new Stack<>();
        second.push("");
        n=0;
        end=0;
        for (int i = 0; i < sb.length(); ) {
            k=0;
            char ch = sb.charAt(i);
            s = "";
//        	System.out.println(ch);
            while(i<sb.length()&&(((sb.charAt(i)-'0')>=0&&(sb.charAt(i)-'0')<=9)||sb.charAt(i)=='.')) {
                s += sb.charAt(i);
                k++;
                i++;
            }
            if(k>0) {
                if(k>1&&sb.charAt(i-1)=='.') {
                    s = s + "0";
                }
                if(k>1&&sb.charAt(i-k)=='.') {
                    s = "0" + s;
                }
                if(i>k+1&&sb.charAt(i-k-1)==')') {
                    second.push("*");
                }
                first.add(s);
                if(i==sb.length()&&end==0) {
                    end=1;
                    while(n>0) {
                        sb.append(")");
                        n--;
                    }
                }
                continue;
            }

            if(ch=='-') {
                if((i==0&&sb.charAt(i+1)!='(') || (i>0&&(sb.charAt(i-1)=='*'||sb.charAt(i-1)=='/'||sb.charAt(i-1)=='('))) {
                    i++;
                    point=0;
                    while(i<sb.length()&&((sb.charAt(i)-'0')>=0&&(sb.charAt(i)-'0')<=9||sb.charAt(i)=='.')) {
                        s = s + sb.charAt(i);
                        k++;
                        if(sb.charAt(i)=='.') {
                            point = 1;
                        }
                        i++;
                    }
                    if(point==1) {
                        if(k>1&&sb.charAt(i-1)=='.') {
                            s = s + "0";
                            s = "-" + s;
                        }
                        if(k>1&&sb.charAt(i-k)=='.') {
                            s = "0" + s;
                            s = "-" + s;
                        }
                        if(sb.charAt(i-1)!='.'&&sb.charAt(i-k)!='.') {
                            s = "-" + s;
                        }
                    }else {
                        s = "-" + s;
                    }
                    first.add(s);
                    if(i==sb.length()&&end==0) {
                        end=1;
                        while(n>0) {
                            sb.append(")");
                            n--;
                        }
                    }
                    continue;
                }
                if(i==0&&sb.charAt(1)=='(') {
                    first.add("0");
                    second.push("-");
                    i++;
                    continue;
                }
                while(!"(".equals(second.peek()) && !"".equals(second.peek())) {
                    first.add(second.pop());
//                      second.push(ch+"");
                }
                second.push(ch+"");
                i++;
            } else if(ch=='+') {
                while(!"(".equals(second.peek()) && !"".equals(second.peek())) {
                    first.add(second.pop());
//                  second.push(ch+"");
                }
                second.push(ch+"");
                i++;
            } else if(ch=='*'||ch=='/') {
                while(!"(".equals(second.peek()) && !"+".equals(second.peek()) &&
                        !"-".equals(second.peek()) && !"".equals(second.peek())) {
                    first.add(second.pop());
//                  second.push(ch+"");
                }
                second.push(ch+"");
                i++;
            } else if(ch=='(') {
                if(i>0&&((sb.charAt(i-1)-'0'>=0&&sb.charAt(i-1)-'0'<=9)||sb.charAt(i-1)=='.'||sb.charAt(i-1)==')')) {
                    second.push("*");
                }
                second.push(ch+"");
                i++;
                n++;
            } else if(ch==')') {
                while(!"(".equals(second.peek())&&!"".equals(second.peek())) {
                    first.add(second.pop());
                }
                second.pop();
                i++;
                n--;
            }
            if(i==sb.length()&&end==0) {
                end=1;
                while(n>0) {
                    sb.append(")");
                    n--;
                }
            }
        }

        while(!"".equals(second.peek())) {
            first.add(second.pop());
        }

//        System.out.println(first);
        return realAnswer().toString();
    }

    private String realAnswer() {//计算
        Deque<BigDecimal> stack = new LinkedList();
        int n = first.size();
        for (int i = 0; i < n; i++) {
            String token = first.get(i);
            System.out.println(token);
            if(isNumber(token)) {
                stack.push(new BigDecimal(token));
            } else {
                BigDecimal num1 = stack.pop();
                BigDecimal num2 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(num2.add(num1));
                        break;
                    case "-":
                        stack.push(num2.subtract(num1));
                        break;
                    case "*":
                        stack.push(num2.multiply(num1));
                        break;
                    case "/":
                        stack.push(num2.divide(num1, 15,BigDecimal.ROUND_HALF_UP));//除数，小数点后几位，四舍五入
                        break;
                    default:
                }
            }
        }
        String s = deleteZero(stack.pop().toString());
        return s;
    }

    private boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }

    public String deleteZero(String s) {//去零
        if("0E-15".equals(s)) {
            return "0";
        }
        if(s.indexOf(".") > 0){

            s = s.replaceAll("0+?$", "");//去掉多余的0

            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉

        }
        return s;
    }
}
