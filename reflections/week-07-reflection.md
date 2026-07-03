# Week 7 Reflection

**Name:** Sadiq Ahmed

**Date:** 7/2/2026

---

## Commits This Week

<!-- Paste a link to your commits for this week. The easiest way: go to your repo on GitHub,
     click "commits", and copy the URL after filtering by your name or branch. -->

**Link:** https://github.com/ahmedsadiq04/media-tracker-android/pull/9

---

## Code Review

<!-- Every week you leave a review on a pod mate's pull request. Fill in both parts below.
     Part 1 is the link — I will verify the review exists on GitHub.
     Part 2 is your written assessment — what you actually looked at and what you found. -->

**Reviewed:** Ilyas
**Link to my review:** https://github.com/Ilyas9805/media-tracker-android/pull/8

### What I Looked At
I took a look at the only files that we touched this week, the Media.kt for the changes he made on the data, and just like mine, he added the details as nullable strings, so if the server does not return these values, the JSON serializer omits them, and the MediaDetailScreen.kt

### What I Noticed
Two things that I noticed are the use of the experimental API, which is a neutral comment due to it just being a reminder and acknowledgement of using an API that may change or break things, and my more important suggestion is the reviews. Currently, the reviews are set up to just render on load, which is not bad for a few, but if this app were to go into production, rendering hundreds, if not thousands, of reviews is not good for low-end devices and could lead to just bloat. Even at just a few hundred, it's an unsafe practice, where either the server should send a specific number, or have the client lazy-load it in some kind of lazy-loaded container. I just wrote it as a comment in something to look for.

### Comments I Left
Firstly, I noticed that you added an Experimental Material3 API class, which may or may not be good, but is something to look out for since this API is in the name experimental and could cause breaking changes. Another thing I would note is that for the reviews, you may want to implement some kind of lazy loading where you only display the first couple of reviews. For the reviews, you could recieve hundreds of reviews from the API at once and the bottleneck is not the data comming back as worst case-scenario, a JSON with 100 elemetns could be around 3-5kb, showing all of them would firstly bloat the load of the app causing it to perform bad on low-end devices, and in the best case could break the UI with a near-limitless scroll to showcase the comments.

---

## One Thing I Understood More Deeply
One thing that was clarified this week is that you are looking for this project to be close but not fully exact, because the website, as you said, was made with React. It's going to be very difficult to try and make it one-for-one. Another thing I really enjoyed this week is getting more familiar with the structure of Kotlin / AndroidX, with Components having their modification such as styling and data in the parentheses, `(text=” ABC”, modifier = Modifier.FillMaxWidth()`. I also kinda got used to having variables placed randomly, like in React, you cannot have a variable inside the return statement for the UI, you gotta have it at the top, but in Kotlin, for my about section and reviews seciton, I needed a variable set by a custom function that turns the reviews from a raw number, such as 15,500,000 to a formatted number such as 15.5M, and having it be right above my Text component is very nice feature of Kotlin. 


---

## One Thing I'm Still Confused About
Because this week we haven't really touched on the API, I still think the Java / Kotlin way of doing API calls is still very new to me. Another thing is Icons, but only partially. At the start, we used to download Icons from Google’s Material UI site and place them in the drawables section, and it made sense to at least cover them, especially for Icons and Images that are custom like the App Icons, but as I was expirementing and exploring, I found the Icon and IconButton compoenent which just take a native material Icon like `Icons.Filled.Add` for example and this made it much easier to use, so my question is am I right about this where having Icons/images in Drawable is only used for cusotm icons and if you are using the MUI ones, you might as well use the native ones right? At the start of today, I was a bit confused about AsyncImage loading from the URL, but as I searched the codebase, experimented with it, and just looked up the documentation for it, I’m starting to like it as it's just a more complicated way of saying `<Image/>` in HTML.

---

## Anything Else *(optional)*
I personally think that sometimes this class runs super fast and its not because of the speed you explain things but mainly becuase of how programming just is, like you think of an idea and you try to implement it but that takes time and truly the testing in Android Studio, because im unfamilur with it, kinda sucks, I really wished it had some kind of Hot-Reloading for the changes I made.

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
