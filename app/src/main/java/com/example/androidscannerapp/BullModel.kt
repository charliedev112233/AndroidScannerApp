package com.example.androidscannerapp


import java.util.*

data class BullModel(
    var bullCode: String = "",
    var dateCode: Int = getAutoId()

)
{
companion object {
    fun getAutoId(): Int {
        val random = Random()
        return random.nextInt(100)
    }


}

}