package com.example.youtube.ui

interface LiveCycle <V> {
    fun bind(v : V)
    fun unbind()
}