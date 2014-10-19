package com.android.internal.telephony;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface ITelephony
  extends IInterface
{
  public abstract void answerRingingCall()
    throws RemoteException;
  
  public abstract boolean endCall()
    throws RemoteException;
  
  public abstract void silenceRinger()
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements ITelephony
  {
    private static final String DESCRIPTOR = "com.android.internal.telephony.ITelephony";
    static final int TRANSACTION_answerRingingCall = 2;
    static final int TRANSACTION_endCall = 1;
    static final int TRANSACTION_silenceRinger = 3;
    
    public Stub()
    {
      attachInterface(this, "com.android.internal.telephony.ITelephony");
    }
    
    public static ITelephony asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.android.internal.telephony.ITelephony");
      if ((localIInterface != null) && ((localIInterface instanceof ITelephony))) {
        return (ITelephony)localIInterface;
      }
      return new Proxy(paramIBinder);
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.android.internal.telephony.ITelephony");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.android.internal.telephony.ITelephony");
        boolean bool = endCall();
        paramParcel2.writeNoException();
        if (bool) {}
        for (int i = 1;; i = 0)
        {
          paramParcel2.writeInt(i);
          return true;
        }
      case 2: 
        paramParcel1.enforceInterface("com.android.internal.telephony.ITelephony");
        answerRingingCall();
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.android.internal.telephony.ITelephony");
      silenceRinger();
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class Proxy
      implements ITelephony
    {
      private IBinder mRemote;
      
      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }
      
      public void answerRingingCall()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.android.internal.telephony.ITelephony");
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public IBinder asBinder()
      {
        return this.mRemote;
      }
      
      /* Error */
      public boolean endCall()
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_1
        //   2: invokestatic 24	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore_2
        //   6: invokestatic 24	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   9: astore_3
        //   10: aload_2
        //   11: ldc 26
        //   13: invokevirtual 30	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   16: aload_0
        //   17: getfield 15	com/android/internal/telephony/ITelephony$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   20: iconst_1
        //   21: aload_2
        //   22: aload_3
        //   23: iconst_0
        //   24: invokeinterface 36 5 0
        //   29: pop
        //   30: aload_3
        //   31: invokevirtual 39	android/os/Parcel:readException	()V
        //   34: aload_3
        //   35: invokevirtual 50	android/os/Parcel:readInt	()I
        //   38: istore 6
        //   40: iload 6
        //   42: ifeq +13 -> 55
        //   45: aload_3
        //   46: invokevirtual 42	android/os/Parcel:recycle	()V
        //   49: aload_2
        //   50: invokevirtual 42	android/os/Parcel:recycle	()V
        //   53: iload_1
        //   54: ireturn
        //   55: iconst_0
        //   56: istore_1
        //   57: goto -12 -> 45
        //   60: astore 4
        //   62: aload_3
        //   63: invokevirtual 42	android/os/Parcel:recycle	()V
        //   66: aload_2
        //   67: invokevirtual 42	android/os/Parcel:recycle	()V
        //   70: aload 4
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	Proxy
        //   1	56	1	bool	boolean
        //   5	62	2	localParcel1	Parcel
        //   9	54	3	localParcel2	Parcel
        //   60	11	4	localObject	Object
        //   38	3	6	i	int
        // Exception table:
        //   from	to	target	type
        //   10	40	60	finally
      }
      
      public String getInterfaceDescriptor()
      {
        return "com.android.internal.telephony.ITelephony";
      }
      
      public void silenceRinger()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.android.internal.telephony.ITelephony");
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
  }
}
