# Week 2 Reflection

**Name:** Sadiq Ahmed

**Date:** 6/4/2026

---

## Commits This Week

<!-- Paste a link to your commits for this week. The easiest way: go to your repo on GitHub,
     click "commits", and copy the URL after filtering by your name or branch. -->

**Link:** [Week 3 Pull-Request](https://github.com/ahmedsadiq04/media-tracker-android/pull/5)

---

## Code Review

<!-- Every week you leave a review on a pod mate's pull request. Fill in both parts below.
     Part 1 is the link — I will verify the review exists on GitHub.
     Part 2 is your written assessment — what you actually looked at and what you found. -->

**Reviewed:** NChyrklund
**Link to my review:** https://github.com/NChyrklund/media-tracker-android/pull/3#event-26365409011

### What I Looked At
I looked at the changes that were regarding the stuff we touched which is the partial API changes,
due to us having a lack of class time and my pod-mates had the same issue where we were kind of confused
in how it was handled / how we send data.

I also saw the AuthViewmodel, and how he splitted it from Login and REgister (login being AUthViewmodel),
I personally think that it should be a combined class since it has many shared elements, but since its required,
I will split mine, right now mine is combined but plan on splitting it.

### What I Noticed
Mainly written from above is that we had to update the strings.xml,
as well as the gradle. The changes that I noticed the most and made sure to double check was the main things that we worked on this week which was the login / registration screen, although I could not validate the login auth since we did not have the client ID or secret yet,

### Comments I Left
I wrote LGTM and specified that we currently have not a lot of the code completed leaving the project in a not-so-good state, espcially with the API half done, so until we get deeper into the API and how we should handle that, then its just an intermidiary pull request.

---

## One Thing I Understood More Deeply
Having all the edits in strings.xml was pretty cool to see,
but there was not much I understood that much deeply, as it was a simple week of just editing
the stuff that was pre-exisitng and dealing with the gradle.

---

## One Thing I'm Still Confused About
I dislike gradle, and barly like it due to the heavy nature, I'm way more used to light-weight projects like in golang, but I understand that its something that I've gotta deal with if I want to learn how to create Native Android Applicaitons.
Additionally, I barley know how to use the REST API handler. I've used stuff like fetch or go or even cpp, but the way that Kotlin uses it has me thinking we have a request and a resposne class or interface of some kind, but only time will tell in how were going to use it.

---

## Anything Else *(optional)*
N/A

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
