package com.example.lab_0.data


data class Course(
    val title: String,
    val code: String,
    val creditHours: Int,
    val description: String,
    val prerequisites: String
)


object CourseDataProvider {
    val courses = listOf(
        Course(
            title = "Introduction to Programming",
            code = "CS101",
            creditHours = 3,
            description = "A foundational course covering basic programming concepts.",
            prerequisites = "None"
        ),
        Course(
            title = "Data Structures and Algorithms",
            code = "CS201",
            creditHours = 4,
            description = "Explores fundamental data structures and algorithmic techniques.",
            prerequisites = "CS101"
        ),
        Course(
            title = "Calculus I",
            code = "MA101",
            creditHours = 4,
            description = "An introductory course to differential calculus.",
            prerequisites = "High School Algebra"
        ),
        Course(
            title = "Linear Algebra",
            code = "MA201",
            creditHours = 3,
            description = "Covers vectors, matrices, and linear transformations.",
            prerequisites = "MA101"
        ),
        Course(
            title = "Web Development Fundamentals",
            code = "IT201",
            creditHours = 3,
            description = "Introduction to HTML, CSS, and basic JavaScript.",
            prerequisites = "None"
        ),
        Course(
            title = "Mobile Application Development",
            code = "CS305",
            creditHours = 4,
            description = "Learn to build applications for mobile platforms.",
            prerequisites = "CS201"
        ),
        Course(
            title = "Database Management Systems",
            code = "IT301",
            creditHours = 3,
            description = "Principles and practices of database design and management.",
            prerequisites = "CS201"
        ),
        Course(
            title = "Operating Systems",
            code = "CS301",
            creditHours = 4,
            description = "Fundamental concepts of operating system design and implementation.",
            prerequisites = "CS201"
        )
    )
}