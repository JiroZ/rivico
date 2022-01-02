package com.rivico.contentservice.dto.blog


import com.bloggie.contentservice.dto.user.PublicProfile
import com.bloggie.contentservice.entities.BlogAccessStatus
import com.bloggie.contentservice.entities.BlogCategory
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.validation.constraints.Size

@Document(collection = "blogs")
data class Blog(
    @Id val id: String,

    @field:Size(min = 3, max = 30, message = "Blog Title Should Be Between 3 & 30 Characters.")
    val blogTitle: String,

    @field:Size(min = 3, max = 50000, message = "Blog range Should Be Between 3 & 50,000 Characters.")
    val data: String,

    val date: Date,
    val owner: PublicProfile,
    val blogAccessStatus: BlogAccessStatus,
    val blogCategory: BlogCategory,
    val views: Int,
    val comments: MutableList<String>,
    val sharedWith: MutableList<PublicProfile>
)