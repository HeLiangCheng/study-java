package com.hlc.study.java.concurrent.day01.demo4;
/**
    *@ClassName DirtyRead
    *@Description todo
    *@Author Liang
    *@Date 2019/1/11 10:53
    *@Version 1.0

 分析：
 1.如果setUser,getUser均不为同步方法，会出现脏读，当线程t1赋值username时，线程t2读取当前User对象，此时password=null
 2.如果setUser为synchronized同步方法，getUser不为同步方法，会出现脏读
 3.如果setUser,getUser均为synchronized同步方法时，不会出现脏读，setUser,getUser对于同一个线程为一组原子操作

 案例
       数据库的一致性 undo

 **/
public class DirtyRead {

    static class User {
        private String username;
        private String password;

        public synchronized String getUser() {
            return "[name=" + username + " ; password=" + password + "]";
        }

        public synchronized void setUser(String username, String password) {
            this.username = username;
            try {
                Thread.sleep(2000);
            }catch (Exception ex){ex.printStackTrace();}
            this.password = password;
        }
    }

    public static void main(String[] args) {
        final User user = new User();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                user.setUser("jim", "123456");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                String usestr = user.getUser();
                System.out.println(usestr);
            }
        });

        t1.start();
        t2.start();

    }
}
