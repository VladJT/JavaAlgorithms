package com.company.ext_operators

// обычный вариант
//TextView(this).visibility = VISIBLE

//inline property
//TextView(this).isVisible = true

// private inline TextView.isVisible: Boolean{
// set(value) = {visibility = if(value) VISIBLE else GONE}
// get() = (visibility==VISIBLE)
// }