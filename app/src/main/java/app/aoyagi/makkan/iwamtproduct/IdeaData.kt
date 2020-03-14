package app.aoyagi.makkan.iwamtproduct

data class IdeaData(
    var title_text: String,
    var content_text: String,
    var pourpose_text: String,
    var heart_count: Int = 0,
    var comment_count: Int = 0
)