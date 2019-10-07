package com.example.qcsproject.model

import com.google.gson.annotations.SerializedName



data class Commit (

    @SerializedName("author")
    val commit_author : CommitAuthor,
    val committer : Committer,
    val message : String
)