package com.uilover.project1932.Activity

import ChatAdapter
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import com.google.ai.client.generativeai.GenerativeModel
import com.uilover.project1932.BuildConfig
import com.uilover.project1932.R
import kotlinx.coroutines.launch

class Ai_Page : AppCompatActivity() {
    private lateinit var searchField: EditText
    private lateinit var sendBtn: ImageButton
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var generativeModel: GenerativeModel

    private val chatList = mutableListOf<ChatMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_page)

        // Initialize views
        searchField = findViewById(R.id.editTextMessage)
        sendBtn = findViewById(R.id.sendButton)
        chatRecyclerView = findViewById(R.id.chatRecyclerView)

        // Setup RecyclerView
        chatAdapter = ChatAdapter(chatList)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = chatAdapter

        // Load model
        loadModel()

        // Send button click listener
        sendBtn.setOnClickListener {
            val userMessage = searchField.text.toString().trim()
            if (userMessage.isNotEmpty()) {
                addMessage(userMessage, isUser = true)
                searchField.text.clear()

                lifecycleScope.launch {
                    performAction(userMessage)
                }
            }
        }
    }

    private fun addMessage(message: String, isUser: Boolean) {
        chatList.add(ChatMessage(message, isUser))
        chatAdapter.notifyItemInserted(chatList.size - 1)
        chatRecyclerView.scrollToPosition(chatList.size - 1)
    }

    private suspend fun performAction(question: String) {
        try {
            val response = generativeModel.generateContent(question)
            val botReply = response.text ?: "No response received"
            addMessage(botReply, isUser = false)
        } catch (e: Exception) {
            addMessage("Error: ${e.message}", isUser = false)
        }
    }

    private fun loadModel() {
        generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey =BuildConfig.API_KEY
        )
    }
}

data class ChatMessage(val message: String, val isUser: Boolean)
