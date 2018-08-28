/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\EeclipseWorkspace\\LeadCertificateService\\src\\com\\example\\leadcertificateservice\\PublicBusiness.aidl
 */
package com.example.leadcertificateservice;
//把允许给activity访问的方法，抽取到这个接口中。然后改这个接口的后缀名为aidl
//然后系统会在gen目录下生成一个与本接口同的java文件，里面有个静态抽象类Stub
//它已经继承了binder类，实现了publicBusiness接口，这个类就是新的中间人

public interface PublicBusiness extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.leadcertificateservice.PublicBusiness
{
private static final java.lang.String DESCRIPTOR = "com.example.leadcertificateservice.PublicBusiness";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.leadcertificateservice.PublicBusiness interface,
 * generating a proxy if needed.
 */
public static com.example.leadcertificateservice.PublicBusiness asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.leadcertificateservice.PublicBusiness))) {
return ((com.example.leadcertificateservice.PublicBusiness)iin);
}
return new com.example.leadcertificateservice.PublicBusiness.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_BZ:
{
data.enforceInterface(DESCRIPTOR);
this.BZ();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.leadcertificateservice.PublicBusiness
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void BZ() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_BZ, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_BZ = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void BZ() throws android.os.RemoteException;
}
