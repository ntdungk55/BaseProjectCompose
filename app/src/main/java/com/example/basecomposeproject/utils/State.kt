package com.example.basecomposeproject.utils

//Enum classes represent a concrete set of values,
//while sealed classes represent a concrete set of classes.
//Since those classes can be object declarations, we can use sealed classes
//to a certain degree instead of enums, but not the other way around.


enum class SampleState(message: String) {
    STATE1("state1"),
    STATE2("state2"),
}

sealed class SampleSealedClass(data: String) {
    class State1(data: String) : SampleSealedClass("State1")
    object State2 : SampleSealedClass("State2")
}