基于 netty的 echo server 和client  
server 可以通过 telnet这样的tcp命令去访问  

启动EchoServer  然后再启动EchoClient 即可看到打印信息

server 要先启动 

主要就是要编写channel handler  和bootstrap 