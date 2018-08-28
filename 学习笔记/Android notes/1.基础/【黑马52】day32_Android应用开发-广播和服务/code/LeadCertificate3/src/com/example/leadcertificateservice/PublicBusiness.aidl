package com.example.leadcertificateservice;

//把允许给activity访问的方法，抽取到这个接口中。然后改这个接口的后缀名为aidl
//然后系统会在gen目录下生成一个与本接口同的java文件，里面有个静态抽象类Stub
//它已经继承了binder类，实现了publicBusiness接口，这个类就是新的中间人
interface PublicBusiness {
	void BZ();
}
