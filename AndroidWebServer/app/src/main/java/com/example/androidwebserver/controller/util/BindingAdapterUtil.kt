package com.example.androidwebserver.controller.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import java.io.ByteArrayOutputStream
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*



    //單向綁定
    @RequiresApi(Build.VERSION_CODES.O)
    @BindingAdapter("imgBase64")
    // 擴充方法
    fun ImageView.setImgBase64(base64: String?){ //setter應該要有參數
        // 需要建一個"不是空值的時候"的判斷
        // if (base64 != null){ } 這是java的寫法
        base64?.let {
//            val byteArray = Base64.decode(base64, Base64.DEFAULT)
            val byteArray = Base64.getDecoder().decode(base64)
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            setImageBitmap(bitmap)
        }
    }

    //雙向綁定
    @RequiresApi(Build.VERSION_CODES.O)
    @InverseBindingAdapter(attribute = "imgBase64")
    fun ImageView.getImageBase64(): String? {
        drawable?.let {
            val byteArrayOutputStream = ByteArrayOutputStream()//要先將bitmap壓縮存到輸出流之中，可以理解為暫存區
            //將圖片壓縮為PNG檔(指定壓縮的格式﹑壓縮品質0-100﹑輸出目標(暫存區))
            it.toBitmap().compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()//去暫存區裡面拿壓好的圖片
            return Base64.getEncoder().encodeToString(byteArray)
//            Base64.encodeToString(byteArray, Base64.DEFAULT)//將押好的圖片轉為Base64.DEFAULT格式
        }
        return null
    }

    @BindingAdapter("imgBase64AttrChanged")
    fun ImageView.setOnImgBase64AttrChangedListener(listener: InverseBindingListener) {
        addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ -> listener.onChange() }
    //onChange()可以導致getImageBase64()被呼叫(因為不會呼叫到他)
    }

val SDF = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
@BindingAdapter("text")
fun TextView.setText(Timestamp: Timestamp){
    text = SDF.format(Timestamp)
}
