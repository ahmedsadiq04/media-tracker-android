# Week 10 Reflection

**Name:** Sadiq Ahmed

**Date:** 7/23/2026

---

## Commits This Week

<!-- Paste a link to your commits for this week. The easiest way: go to your repo on GitHub,
     click "commits", and copy the URL after filtering by your name or branch. -->

My code was accedently pushes to main, and I could'nt find a great way to revert
because I have multiple accounts under the same machine, so my code is on this commit
and this PR has the explanation as well.
**Link:** [Commit b661158](https://github.com/ahmedsadiq04/media-tracker-android/commit/b661158d0ecadae78664fdcefe0dae63512b86b3) [Pull-Request](https://github.com/ahmedsadiq04/media-tracker-android/pull/11)

---

## Code Review

<!-- Every week you leave a review on a pod mate's pull request. Fill in both parts below.
     Part 1 is the link — I will verify the review exists on GitHub.
     Part 2 is your written assessment — what you actually looked at and what you found. -->

**Reviewed:** Nicholas
**Link to my review:** https://github.com/NChyrklund/media-tracker-android/pull/10
**My Comment** https://github.com/NChyrklund/media-tracker-android/pull/10#pullrequestreview-4769736054

### What I Looked At
I looked at Favorite.kt a new file he made for making requests for items that the user would favorite and left a note,

I also looked at a new file that he added, UpdateLibraryStatusRequest.kt

### What I Noticed
I left a note about Favorite in Favorite.kt because its not clear looking at the data class if its for the request or the resposne or if its for something else entirely.

For UpdateLibraryStatusRequest.kt, I noticed a Struct that contains extra data the status that may potentially cause the server to return 400 BAD REQUEST

Overall, its kinda limited work as he's in the same boat as me, getting back into the rythm of Android Studio / Jetpack Compose, and because of that, there are some stuff to get used to, as well as APIs still being relativly new to us all.

### Comments I Left
Favorite.kt
``` markdown
Just to note, this could lead to potential issues with naming since other devs who look at your code don't know if this the request, the return or other value for Favorite.
```

UpdateLibraryStatusRequest.kt
``` markdown
If this is used by something else, disregard,
Although this uses status and mediaId, it is a PUT libary/media that updates the state of the library does not take in a mediaId in the JSON, which may lead to errors from the server 400 BAD REQUEST
```

---

## One Thing I Understood More Deeply
This week was getting back into the cycle of things, and really getting into how the APIs work. I spent a majority of the first half looking through my code and seening where what goes, and figuring out the different syntax. This week I got really good at understanding the way @seralize works epscially with functions that can be seralized such as the LibraryEnum where its a string as the JSON primitive type, but Kotlin or the seralize library we use takes the string and using a function, converts it to our enum type making it easier to work with in Kotlin.

I also started to use Log.i for info and Log.e for warnings, 

---

## One Thing I'm Still Confused About
I did'nt really do deep research about the Snackbar which should let me log events when the buttons are pressed. For example, when you do Want to, or Save (favorite), it would send a notification to the UI to show you it happend. Right now, it just shows nothing and logs to the console, which is not bad for dev, but for users, it makes teh app not feel responsive which is very bad.

---

## Anything Else *(optional)*


---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
