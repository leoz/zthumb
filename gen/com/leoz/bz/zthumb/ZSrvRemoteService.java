/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\LeoZ\\workspace\\bz\\zthumb\\src\\com\\leoz\\bz\\zthumb\\ZSrvRemoteService.aidl
 */
package com.leoz.bz.zthumb;
public interface ZSrvRemoteService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.leoz.bz.zthumb.ZSrvRemoteService
{
private static final java.lang.String DESCRIPTOR = "com.leoz.bz.zthumb.ZSrvRemoteService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.leoz.bz.zthumb.ZSrvRemoteService interface,
 * generating a proxy if needed.
 */
public static com.leoz.bz.zthumb.ZSrvRemoteService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.leoz.bz.zthumb.ZSrvRemoteService))) {
return ((com.leoz.bz.zthumb.ZSrvRemoteService)iin);
}
return new com.leoz.bz.zthumb.ZSrvRemoteService.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
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
case TRANSACTION_setDir:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.setDir(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setSize:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setSize(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setThreads:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.setThreads(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_startScan:
{
data.enforceInterface(DESCRIPTOR);
this.startScan();
reply.writeNoException();
return true;
}
case TRANSACTION_scanDir:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int[] _arg1;
_arg1 = data.createIntArray();
this.scanDir(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_scanFile:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
this.scanFile(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_stopScan:
{
data.enforceInterface(DESCRIPTOR);
this.stopScan();
reply.writeNoException();
return true;
}
case TRANSACTION_clearCache:
{
data.enforceInterface(DESCRIPTOR);
this.clearCache();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.leoz.bz.zthumb.ZSrvRemoteService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public void setDir(java.lang.String dir) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(dir);
mRemote.transact(Stub.TRANSACTION_setDir, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void setSize(int size) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(size);
mRemote.transact(Stub.TRANSACTION_setSize, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void setThreads(int count) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(count);
mRemote.transact(Stub.TRANSACTION_setThreads, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void startScan() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_startScan, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void scanDir(java.lang.String dir, int[] sizes) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(dir);
_data.writeIntArray(sizes);
mRemote.transact(Stub.TRANSACTION_scanDir, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void scanFile(java.lang.String file, int size) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(file);
_data.writeInt(size);
mRemote.transact(Stub.TRANSACTION_scanFile, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void stopScan() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stopScan, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void clearCache() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_clearCache, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_setDir = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_setSize = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_setThreads = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_startScan = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_scanDir = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_scanFile = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_stopScan = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_clearCache = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
}
public void setDir(java.lang.String dir) throws android.os.RemoteException;
public void setSize(int size) throws android.os.RemoteException;
public void setThreads(int count) throws android.os.RemoteException;
public void startScan() throws android.os.RemoteException;
public void scanDir(java.lang.String dir, int[] sizes) throws android.os.RemoteException;
public void scanFile(java.lang.String file, int size) throws android.os.RemoteException;
public void stopScan() throws android.os.RemoteException;
public void clearCache() throws android.os.RemoteException;
}
