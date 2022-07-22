package com.example.androidscannerapp

class ScannerResult {


    var bullCode: String
    var dateCode: Int
    var barn: String

    constructor (
       bullCode: String = "", dateCode: Int = 0, barn: String = ""

    ) {
        this.bullCode = bullCode
        this.dateCode = dateCode
        this.barn = barn
    }

}



