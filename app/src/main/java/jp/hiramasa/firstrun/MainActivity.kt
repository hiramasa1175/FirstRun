package jp.hiramasa.firstrun

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import jp.hiramasa.firstrun.R.id.formula
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

  private var nStr: String = ""
  private val nList = mutableListOf<Long>()
  private val oList = mutableListOf<Char>()

  lateinit var mAdView: AdView

  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
    MobileAds.initialize(this, "ca-app-pub-8221838827607460~8884445270")

    mAdView = findViewById(R.id.adView)
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

      calculator()
    }

    btn_1.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}1"
      nStr += "1"

      calculator()
    }

    btn_2.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}2"
      nStr += "2"

      calculator()
    }

    btn_3.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}3"
      nStr += "3"

      calculator()
    }

    btn_4.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}4"
      nStr += "4"

      calculator()
    }

    btn_5.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}5"
      nStr += "5"

      calculator()
    }

    btn_6.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}6"
      nStr += "6"

      calculator()
    }

    btn_7.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}7"
      nStr += "7"

      calculator()
    }

    btn_8.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}8"
      nStr += "8"

      calculator()
    }

    btn_9.setOnClickListener {
      if (btn_DEL.text == "CLR") clear()
      formula.text = "${formula.text}9"
      nStr += "9"

      calculator()
    }

    btn_plus.setOnClickListener {
      if (formula.text == "") return@setOnClickListener
      if (formula.text.last() == '+') return@setOnClickListener
      if (formula.text.last() == '−') return@setOnClickListener
      if (formula.text.last() == '×') return@setOnClickListener
      if (formula.text.last() == '÷') return@setOnClickListener

      if (btn_DEL.text == getString(R.string.btn_CLR)) btn_DEL.text = getString(R.string.btn_DEL)

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

      if (btn_DEL.text == getString(R.string.btn_CLR)) btn_DEL.text = getString(R.string.btn_DEL)

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

      if (btn_DEL.text == getString(R.string.btn_CLR)) btn_DEL.text = getString(R.string.btn_DEL)

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

      if (btn_DEL.text == getString(R.string.btn_CLR)) btn_DEL.text = getString(R.string.btn_DEL)

      formula.text = "${formula.text}÷"
      addList(nStr, '÷')
      nStr = ""
    }

    btn_DEL.setOnClickListener {
      if (formula.text == "") return@setOnClickListener

      if (btn_DEL.text == getString(R.string.btn_DEL)) {
        val formulaStr = formula.text.toString()
        formula.text = formulaStr.subSequence(0, formulaStr.lastIndex)
        if (!nStr.isEmpty()) {
          nStr = nStr.substring(0, nStr.lastIndex)
        } else if (!oList.isEmpty()) {
          nStr = nList.last().toString()
          nList.remove(nList.last())
          oList.remove(oList.last())
        }
      } else {
        clear()
      }

      if (!nStr.isEmpty()) calculator()
      else if (formula.text == "") clear()
    }

    btn_DEL.setOnLongClickListener {
      clear()
      return@setOnLongClickListener true
    }

    btn_OFF.setOnClickListener {
    }

    btn_equal.setOnClickListener {
      if (formula.text == "") return@setOnClickListener
      if (oList.isEmpty()) return@setOnClickListener
      if (formula.text.last() == '+') return@setOnClickListener
      if (formula.text.last() == '−') return@setOnClickListener
      if (formula.text.last() == '×') return@setOnClickListener
      if (formula.text.last() == '÷') return@setOnClickListener

      val result = calculator().toString()
      formula.text = result
      nStr = result
      nList.clear()
      oList.clear()
      btn_DEL.text = getString(R.string.btn_CLR)
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
    taxExcluded.text = getString(R.string.taxExcluded)
    taxIncluded.text = getString(R.string.taxIncluded)
    nStr = ""
    nList.clear()
    oList.clear()
    btn_DEL.text = getString(R.string.btn_DEL)
  }

  @SuppressLint("SetTextI18n")
  private fun calculator(): Long {
    val nList = nList.toMutableList()
    val oList = oList.toMutableList()
    nList.add(nStr.toLong())

    oList
        .filter { it == '×' || it == '÷' }
        .map {
          val i = oList.indexOfFirst { it == '×' || it == '÷' }
          val result = if (it == '×') nList[i] * nList[i + 1] else nList[i] / nList[i + 1]
          nList[i] = result
          nList.removeAt(i + 1)
          oList.removeAt(i)
        }

    oList
        .filter { it == '+' || it == '−' }
        .map {
          val result = if (it == '+') nList[0] + nList[1] else nList[0] - nList[1]
          nList[0] = result
          nList.removeAt(1)
          oList.removeAt(0)
        }

    taxExcluded.text = getString(R.string.taxExcluded) + nList[0]
    taxIncluded.text = getString(R.string.taxIncluded) + (nList[0] * 1.08).toLong()

    Log.d("##################", "##################")
    Log.d("this.nStr", nStr)
    Log.d("this.nList", this.nList.toString())
    Log.d("this.oList", this.oList.toString())
    Log.d("local.nList", nList.toString())
    Log.d("local.oList", oList.toString())
    Log.d("result", nList[0].toString())

    return (nList[0] * 1.08).toLong()

  }
}
