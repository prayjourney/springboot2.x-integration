### 文件的复制
有fileInputStream, FileChannelIn, 也有有fileOutputStream, FileChannelOut, in是读入到内存, out是写入到磁盘。

### 对于网络传送
in是读入到内存, out是网络的传输, 所以就完成了替代, 在client中, 发送信息就要使用SocketChannel, 就是out,
对于server, serverSocketChannel就是in, 而写入到磁盘就是out了。这个是单向的模式, 双向的就需要另外去实现和考虑了。