package com.example.tdahelper.model


class BillFileD(
    override val name: String,
    val rowStrings: List<String>
) : FileD {
    private val regex = Regex("""^(\d{2}\/\d{2})\s+(.+?)\s+(\d+,\d{2})$""")
    override val rows = rowStrings.mapNotNull { line ->
        regex.matchEntire(line)?.destructured?.let { (date, desc, amount) ->
            FileRow(
                date = date,
                description = desc,
                amount = amount,
                category = ""
            )
        }
    }

}
