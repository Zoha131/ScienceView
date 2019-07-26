package io.github.zoha131.scienceview.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import io.github.zoha131.scienceview.ScienceView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var scienceView: ScienceView<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scienceView = findViewById(R.id.scienceView)
    }

    override fun onResume() {
        super.onResume()

        scienceView.addCSS(R.raw.exam)
        scienceView.addCSS(R.raw.layers)
        scienceView.addCSS(R.raw.style)

        scienceView.setModel { model ->
            """
                    
                <div class="question" data-id="23449" id="question_1" question-number="1">
              <p class="hideonpause" id="questionLabel"><h2><strong></strong></h3></p>
              <p class="hideonpause qstn" id="questionDescription">
              </p>
              <div class="options hideonpause">

                <!-- <input type="hidden" class="j-question-id" value="23449" name="question_answer[id][]">
                <input type="hidden" class="j-time-taken" value="" id="time_taken1" name="question_answer[time][]">
                <input type="hidden" class="j-question-type" value="" id="type1" name="question_answer[type][]"> -->

                                <!-- Start Options -->
                <div class="op_div" id="1a" onclick="onClick('div',1)">
                  <input  type="radio" value="1" class="hidden j-radio" name="question_answer[answer][0]"  id="0optiona"><span class="option-alphabet">A</span><label id="option1" for="0optiona">${'$'}${'$'}\\ce{x Na(NH4)HPO4 ->[\\Delta] (NaPO3)_x + x NH3 ^ + x H2O}${'$'}${'$'}</label>
                </div>
                <div class="op_div" id="1b" onclick="onClick('div',2)">
                  <input  type="radio" value="2" class="hidden j-radio" name="question_answer[answer][0]" id="0optionb"><span class="option-alphabet">B</span><label id="option2" for="0optionb">${'$'}\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}${'$'}</label>
                </div>
                <div class="op_div" id="1c" onclick="onClick('div',3)">
                  <input  type="radio" value="3" class="hidden j-radio" name="question_answer[answer][0]" id="0optionc"><span class="option-alphabet">C</span><label id="option3" for="0optionc">$model</label>
                </div>
                <div  class="op_div" id="1d" onclick="onClick('div',4)">
                  <input  type="radio" value="4" class="hidden j-radio" name="question_answer[answer][0]" id="0optiond"><span class="option-alphabet">D</span><label id="option4" for="0optiond">dfadsfa</label>
                </div>
                <div  class="op_div" id="1e" onclick="onClick('div',5)">
                  <input  type="radio" value="5" class="hidden j-radio" name="question_answer[answer][0]" id="0optione"><span class="option-alphabet">E</span><label id="option5" for="0optione"></label>
                </div>
                                <!-- End Options -->
              </div>          
            </div>
                    
                """.trimIndent()
        }

        scienceView.setScienceClickListener { tag, data ->
            Log.d("JSCLICKLISTENER", "$tag $data")
        }

        button.setOnClickListener {
            scienceView.setData("Hello Vello")
        }
    }
}
