package com.example.kotlinstudy.utils

object Constants {

    object API {
        const val BASE_URL = "https://www.reddit.com/"

        const val GET_POSTS = "r/aww/hot.json"
    }

    object CHAR {
        const val DOT = "."
        const val EMPTY = ""
        const val SPACE = " "
        const val SLASH = "/"
        const val COMMA = ","
        const val SHARP = "#"
        const val PLUS = "+"
        const val ARROW = " > "
        const val PERCENT = "%"
        const val EQUALLY = "="
        const val HYPHEN = " - "
        const val UNDERSCORE = "_"
        const val AMPERSAND = "&"
        const val OPEN_BRACE = "("
        const val CLOSE_BRACE = ")"
        const val DOUBLE_HYPHEN = "--"
        const val COMMA_WITH_LINE_BREAK = ",\n"
    }

    object CONFIG {
        const val PLATFORM = "Platform"
        const val LANGUAGE = "Language"
        const val DEVICE_ID = "DeviceId"
        const val PASSWORD = "password"
        const val ANDROID = "Android"
        const val WEB_VIEW_ENCODING = "UTF-8"
    }

    object INTERACTION {
        const val PACKAGE = "package"
        const val HTTP_URI = "http://"
        const val HTTPS_URI = "https://"
    }

    object DELAY {
        const val SPLASH = 3 // seconds
        const val BASE = 1 // seconds
    }

    object NETWORK {
        const val TIMEOUT = 30L // seconds
        const val CACHE_SIZE = 10 * 1024 * 1024L //10MB cache
        const val GSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"
    }

    object DB {
        const val NAME = "kotlin-database"
    }

    object NUM {
        const val PASSWORD_MIN_LENGTH = 6
        const val POST_DEF_LOAD_SIZE = 40
        const val POST_PREFETCH_DISTANCE = 10
    }

    //  Server
    const val USERS = "users"
    const val LIMIT = "limit"
    const val AFTER = "after"
    const val BEFORE = "before"
    const val ICON_URL = "icon_url"
    const val NUM_COMMENTS = "num_comments"
    const val ALL_AWARDINGS = "all_awardings"
}