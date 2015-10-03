/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\mudassir\\StudioProjects\\rig1\\app\\src\\main\\aidl\\com\\example\\rig\\ISignalProvider.aidl
 */
package com.example.rig;
public interface ISignalProvider extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.rig.ISignalProvider
{
private static final java.lang.String DESCRIPTOR = "com.example.rig.ISignalProvider";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.rig.ISignalProvider interface,
 * generating a proxy if needed.
 */
public static com.example.rig.ISignalProvider asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.rig.ISignalProvider))) {
return ((com.example.rig.ISignalProvider)iin);
}
return new com.example.rig.ISignalProvider.Stub.Proxy(obj);
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
case TRANSACTION_start:
{
data.enforceInterface(DESCRIPTOR);
this.start();
reply.writeNoException();
return true;
}
case TRANSACTION_setTest:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setTest(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_setCommit:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.setCommit(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_startLearning:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.startLearning(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_stopLearning:
{
data.enforceInterface(DESCRIPTOR);
this.stopLearning();
reply.writeNoException();
return true;
}
case TRANSACTION_startTesting:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.startTesting(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_stopTesting:
{
data.enforceInterface(DESCRIPTOR);
this.stopTesting();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.rig.ISignalProvider
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
@Override public void start() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_start, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setTest(boolean a) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((a)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setTest, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void setCommit(boolean a) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((a)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setCommit, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void startLearning(java.lang.String trainingSetName, java.lang.String gestureName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(trainingSetName);
_data.writeString(gestureName);
mRemote.transact(Stub.TRANSACTION_startLearning, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void stopLearning() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stopLearning, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void startTesting(java.lang.String activeTrainingSet) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(activeTrainingSet);
mRemote.transact(Stub.TRANSACTION_startTesting, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void stopTesting() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stopTesting, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_start = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_setTest = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_setCommit = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_startLearning = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_stopLearning = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_startTesting = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_stopTesting = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
}
public void start() throws android.os.RemoteException;
public void setTest(boolean a) throws android.os.RemoteException;
public void setCommit(boolean a) throws android.os.RemoteException;
public void startLearning(java.lang.String trainingSetName, java.lang.String gestureName) throws android.os.RemoteException;
public void stopLearning() throws android.os.RemoteException;
public void startTesting(java.lang.String activeTrainingSet) throws android.os.RemoteException;
public void stopTesting() throws android.os.RemoteException;
}
