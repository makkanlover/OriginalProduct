package app.aoyagi.makkan.iwamtproduct

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_edit_idea.*

class EditIdeaActivity : AppCompatActivity() {
    private val database = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_edit_idea)

        createButton.setOnClickListener {
            sendData()
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    fun sendData() {
        if (titleText.text.toString() != "" &&  content.text.toString() != "" && pourpose.text.toString() != ""){
            val ideaList = hashMapOf(
                    "title" to titleText.text.toString(),
            "content" to content.text.toString(),
            "pourpose" to pourpose.text.toString(),
            "heartcount" to "0",
            "timestamp" to FieldValue.serverTimestamp()

            )

            database.collection("users")
                .add(ideaList)
                .addOnSuccessListener { documentReference ->
                }
                .addOnFailureListener { e ->
                }
        }
    }


}

