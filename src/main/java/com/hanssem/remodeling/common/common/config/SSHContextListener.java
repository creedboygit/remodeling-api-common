package com.hanssem.remodeling.common.common.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
* 로컬에서 개발 디비 사용 시에만 사용
* */
//@Profile("local")
//@WebListener
public class SSHContextListener implements ServletContextListener {

        private SSHConnectionConfig ssh;

        public SSHContextListener() {
            super();
        }

        @Override
        public void contextInitialized(ServletContextEvent arg0) {
            try {
                ssh = new SSHConnectionConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void contextDestroyed(ServletContextEvent arg0) {
            ssh.closeSSH();
        }
}
