package umeapp.jp.firstrun

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

  private var nStr: String = ""
  private val nList = ArrayList<Long>()
  private val oList = ArrayList<Char>()

  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    MobileAds.initialize(applicationContext, "ca-app-pub-3940256099942544~1458002511")
    val mAdView = findViewById<AdView>(R.id.adView)
    val adRequest = AdRequest.Builder().build()
    mAdView.loadAd(adRequest)

    btn_0.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      if (formula.text == "") return@setOnClickListener
      if (formula.text.last() == '+') return@setOnClickListener
      if (formula.text.last() == '−') return@setOnClickListener
      if (formula.text.last() == '×') return@setOnClickListener
      if (formula.text.last() == '÷') return@setOnClickListener

      formula.text = "${formula.text}0"
      nStr += "0"

      taxIncluded.text = calculator().toString()
    }

    btn_1.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}1"
      nStr += "1"

      taxIncluded.text = calculator().toString()
    }

    btn_2.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}2"
      nStr += "2"

      taxIncluded.text = calculator().toString()
    }

    btn_3.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}3"
      nStr += "3"

      taxIncluded.text = calculator().toString()
    }

    btn_4.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}4"
      nStr += "4"

      taxIncluded.text = calculator().toString()
    }

    btn_5.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}5"
      nStr += "5"

      taxIncluded.text = calculator().toString()
    }

    btn_6.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}6"
      nStr += "6"

      taxIncluded.text = calculator().toString()
    }

    btn_7.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}7"
      nStr += "7"

      taxIncluded.text = calculator().toString()
    }

    btn_8.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}8"
      nStr += "8"

      taxIncluded.text = calculator().toString()
    }

    btn_9.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}9"
      nStr += "9"

      taxIncluded.text = calculator().toString()
    }

    btn_plus.setOnClickListener {
      if (formula.text == "") return@setOnClickListener
      if (formula.text.last() == '+') return@setOnClickListener
      if (formula.text.last() == '−') return@setOnClickListener
      if (formula.text.last() == '×') return@setOnClickListener
      if (formula.text.last() == '÷') return@setOnClickListener

      if (btn_DEL.text == "CLR") btn_DEL.text = "DEL"

      formula.text = "${formula.text}+"
      addList(nStr, '+')
      nStr = ""
    }

    btn_minus.setOnClickListener {
      if (formula.text == "") return@setOnClickListener
      if (formula.text.last() == '+') return@setOnClickListener
      if (formula.text.last() == '−') return@setOnClickListener
      if (formula.text.last() == '×') return@setOnClickListener
      if (formula.text.last() == '÷') return@setOnClickListener

      if (btn_DEL.text == "CLR") btn_DEL.text = "DEL"

      formula.text = "${formula.text}−"
      addList(nStr, '−')
      nStr = ""
    }

    btn_times.setOnClickListener {
      if (formula.text == "") return@setOnClickListener
      if (formula.text.last() == '+') return@setOnClickListener
      if (formula.text.last() == '−') return@setOnClickListener
      if (formula.text.last() == '×') return@setOnClickListener
      if (formula.text.last() == '÷') return@setOnClickListener

      if (btn_DEL.text == "CLR") btn_DEL.text = "DEL"

      formula.text = "${formula.text}×"
      addList(nStr, '×')
      nStr = ""
    }

    btn_divided.setOnClickListener {
      if (formula.text == "") return@setOnClickListener
      if (formula.text.last() == '+') return@setOnClickListener
      if (formula.text.last() == '−') return@setOnClickListener
      if (formula.text.last() == '×') return@setOnClickListener
      if (formula.text.last() == '÷') return@setOnClickListener

      if (btn_DEL.text == "CLR") btn_DEL.text = "DEL"

      formula.text = "${formula.text}÷"
      addList(nStr, '÷')
      nStr = ""
    }

    btn_DEL.setOnClickListener {
      if (formula.text == "") return@setOnClickListener
      if (btn_DEL.text == "CLR") clear()

      try {
        val formulaStr = formula.text.toString()
        if (!formulaStr.isEmpty()) {
          formula.text = formulaStr.subSequence(0, formulaStr.lastIndex)
        }
        if (!nStr.isEmpty()) {
          nStr = nStr.substring(0, nStr.lastIndex)
        } else if (!oList.isEmpty()) {
          nStr = nList.last().toString()
          nList.remove(nList.last())
          oList.remove(oList.last())
        }
      } catch (e: Exception) {
        println(e)
      }

      if (!nStr.isEmpty()) taxIncluded.text = calculator().toString()
      else if (formula.text == "") taxIncluded.text = ""
    }

    btn_DEL.setOnLongClickListener {
      clear()
      return@setOnLongClickListener true
    }

    btn_OFF.setOnClickListener {
    }

    btn_equal.setOnClickListener {
      if (formula.text == "") return@setOnClickListener
      if (formula.text.last() == '+') return@setOnClickListener
      if (formula.text.last() == '−') return@setOnClickListener
      if (formula.text.last() == '×') return@setOnClickListener
      if (formula.text.last() == '÷') return@setOnClickListener

      addList(nStr)
      val result = calculator().toString()
      formula.text = result
      btn_DEL.text = "CLR"
      nStr = result
      nList.clear()
      oList.clear()
    }
  }

  private fun addList(str: String) {
    try {
      val num = str.toLong()
      nList.add(num)
    } catch (e: Exception) {
      println(e)
    }
  }

  private fun addList(str: String, ope: Char) {
    try {
      val num = str.toLong()
      nList.add(num)
      oList.add(ope)
    } catch (e: Exception) {
      println(e)
    }
  }

  private fun clear() {
    formula.text = ""
    nStr = ""
    nList.clear()
    oList.clear()
    btn_DEL.text = "DEL"
    taxExcluded.text = ""
    taxIncluded.text = ""
  }

  private fun calculator(): Long {
    var i = 0
    val nList = ArrayList<Long>(this.nList)
    val oList = ArrayList<Char>(this.oList)
    nList.add(nStr.toLong())
    try {
      while (i < oList.size) {
        if (oList[i] == '×' || oList[i] == '÷') {
          val result = if (oList[i] == '×') nList[i] * nList[i + 1] else nList[i] / nList[i + 1]
          nList[i] = result
          nList.removeAt(i + 1)
          oList.removeAt(i)
          i--
        } else if (oList[i] == '−') {
          oList[i] = '+'
          nList[i + 1] = nList[i + 1] * -1
        }
        i++
      }
    } catch (e: Exception) {
      println(e)
    }

    var result = 0.toLong()
    for (temp in nList) {
      result += temp
    }

    Log.d("##################", "##################")
    Log.d("this.nStr", nStr)
    Log.d("this.nList", this.nList.toString())
    Log.d("this.oList", this.oList.toString())
    Log.d("local.nList", nList.toString())
    Log.d("local.oList", oList.toString())
    Log.d("result", result.toString())

    return result
  }

}
