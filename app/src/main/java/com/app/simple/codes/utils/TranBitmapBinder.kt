package com.app.simple.codes.utils

import android.graphics.Bitmap
import android.os.Binder
import android.os.IBinder
import android.os.IInterface
import android.os.Parcel
import java.io.FileDescriptor

/**
 * Created by shishoufeng on 2020/6/10.
 * email:shishoufeng1227@126.com
 *
 *
 * 使用 binder 机制 传输bitmap
 *
 */
class TranBitmapBinder : IBinder{

    private val _TAG = this.javaClass.simpleName

    override fun getInterfaceDescriptor(): String? {
        P.outI(_TAG,"getInterfaceDescriptor() ")

        return null
    }

    override fun isBinderAlive(): Boolean {
        P.outI(_TAG,"isBinderAlive() ")

        return false
    }

    override fun linkToDeath(recipient: IBinder.DeathRecipient, flags: Int) {
        P.outI(_TAG,"linkToDeath() ")

    }

    override fun queryLocalInterface(descriptor: String): IInterface? {
        P.outI(_TAG,"queryLocalInterface() ")

        return null
    }

    override fun transact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
        P.outI(_TAG,"transact() ")

        return false
    }

    override fun dumpAsync(fd: FileDescriptor, args: Array<out String>?) {
        P.outI(_TAG,"dumpAsync() ")

    }

    override fun dump(fd: FileDescriptor, args: Array<out String>?) {
        P.outI(_TAG,"dump() ")

    }

    override fun unlinkToDeath(recipient: IBinder.DeathRecipient, flags: Int): Boolean {
        P.outI(_TAG,"unlinkToDeath() ")

        return false
    }

    override fun pingBinder(): Boolean {
        P.outI(_TAG,"pingBinder() ")

        return false
    }
}

class BitmapBinder : Binder(){

    lateinit var bitmap : Bitmap

}