package com.example.studenthardlife

object ExerciseLists {
    val lists: List<ExerciseList> = List(5)
    { outer_it ->
        ExerciseList(
            "List #$outer_it",
            List(2)
            {
                "Exercise #$it"
            }
        )
    }
}