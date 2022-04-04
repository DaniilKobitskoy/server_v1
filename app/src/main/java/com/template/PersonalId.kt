package com.template

import android.content.Context
import java.util.*

class PersonalId {
    fun isPersonalid(ctx: Context): Boolean {
        val personaid = getUuid(ctx)
        return personaid != null
    }

    fun generate(ctx: Context) {
        val profileId = UUID.randomUUID().toString()
        ctx.getSharedPreferences("User", 0)
            .edit()
            .putString("profileId", profileId)
            .apply()
    }

    fun getUuid(ctx: Context): String? {
        return ctx.getSharedPreferences("User", 0)
            .getString("profileId", null)
    }
}