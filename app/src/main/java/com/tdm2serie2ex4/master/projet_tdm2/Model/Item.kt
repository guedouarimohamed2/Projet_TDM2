package com.tdm2serie2ex4.master.projet_tdm2.Model

data class Item(val title:String,val pubDate:String,val link:String,val guid:String,val author:String,
                val thumbnail:String,val description:String,val content:String,val enclosure:Object,val categories:List<String>) {
}