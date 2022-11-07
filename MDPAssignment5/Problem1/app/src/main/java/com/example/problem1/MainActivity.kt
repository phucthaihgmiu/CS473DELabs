package com.example.problem1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var score: Long = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickQ1RadioButton(view: View){
        if(view is RadioButton){
            if(view.id == R.id.rbQ1A && view.isChecked){
                score += 1;
            }
        }
    }

    fun clickQ2RadioButton(view: View){
        if(view is RadioButton){
            if(view.id == R.id.rbQ2A && view.isChecked){
                score += 1;
            }
        }
    }

    fun clickReset(view: View) {
        resetButtons();
    }
    fun resetButtons(){
        score = 0;
        rgQ1.clearCheck();
        rgQ2.clearCheck();
    }

    fun clickSubmit(view: View){
        var result: Long = score * 100 / 2;
        var content = "Your score is ${result}%";
        var title = "Quiz result"
        showAlertDialog(title, content);
    }

    fun showAlertDialog(title: String, content: String){

        var alert : AlertDialog = this.let {
            val builder = AlertDialog.Builder(it);
            builder.apply {
              setPositiveButton("OK"){
                  dialog, _->
                    resetButtons();
                    dialog.dismiss();
              }

            };
            builder
                .setMessage(content)
                .setTitle(title);
            builder.create();
        };

        alert.show();
    }
}