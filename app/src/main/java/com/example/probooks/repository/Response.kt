package com.example.probooks.repository

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("price_opt2_adv")
	val priceOpt2Adv: String? = null,

	@field:SerializedName("faq_num1")
	val faqNum1: String? = null,

	@field:SerializedName("faq_num2")
	val faqNum2: String? = null,

	@field:SerializedName("price_choose")
	val priceChoose: String? = null,

	@field:SerializedName("faq_title")
	val faqTitle: String? = null,

	@field:SerializedName("mail_hyperlink")
	val mailHyperlink: String? = null,

	@field:SerializedName("price_title")
	val priceTitle: String? = null,

	@field:SerializedName("price_opt3")
	val priceOpt3: String? = null,

	@field:SerializedName("price_opt2")
	val priceOpt2: String? = null,

	@field:SerializedName("price_opt3_title")
	val priceOpt3Title: String? = null,

	@field:SerializedName("price_opt1")
	val priceOpt1: String? = null,

	@field:SerializedName("partner2")
	val partner2: String? = null,

	@field:SerializedName("partner1")
	val partner1: String? = null,

	@field:SerializedName("contacts_title")
	val contactsTitle: String? = null,

	@field:SerializedName("price_req")
	val priceReq: String? = null,

	@field:SerializedName("price_opt2_title")
	val priceOpt2Title: String? = null,

	@field:SerializedName("desc_title")
	val descTitle: String? = null,

	@field:SerializedName("welcome")
	val welcome: String? = null,

	@field:SerializedName("price_opt1_title")
	val priceOpt1Title: String? = null,

	@field:SerializedName("price_opt3_adv")
	val priceOpt3Adv: String? = null,

	@field:SerializedName("contacts")
	val contacts: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null,

	@field:SerializedName("partners_title")
	val partnersTitle: String? = null,

	@field:SerializedName("whatsapp_number")
	val whatsapp_number: String? = null
)
