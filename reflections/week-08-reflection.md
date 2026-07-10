# Week 8 Reflection

**Name:** Sadiq Ahmed

**Date:** 7/9/2026

---

## Commits This Week

<!-- Paste a link to your commits for this week. The easiest way: go to your repo on GitHub,
     click "commits", and copy the URL after filtering by your name or branch. -->

**Link:** https://github.com/ahmedsadiq04/media-tracker-android/pull/10
**My Comment** https://github.com/Ilyas9805/media-tracker-android/pull/9#pullrequestreview-4668135592

---

## Code Review

<!-- Every week you leave a review on a pod mate's pull request. Fill in both parts below.
     Part 1 is the link — I will verify the review exists on GitHub.
     Part 2 is your written assessment — what you actually looked at and what you found. -->

**Reviewed:** Ilyas
**Link to my review:** https://github.com/Ilyas9805/media-tracker-android/pull/9

### What I Looked At
I looked at the files that we've heavily 

### What I Noticed
I noticed in his getMediaDetail func, he checks the code between 200 OK and 404 NOT FOUND,
and in this func, if its a 404 NOT FOUND, it checks the fake media repository to return the data found in there,
I find this a bit strange since it might return testing data which is something I commented,

I also noticed he added a UIState which is a realy good practice for handling teh state where theres Loading, Not Found, Error and Success;
which is good practice as it lets the UI handle the different states, although becuase mediaDetail already returns data wrapped in success not found and network error, although I did not comment on it because it makes the state have only 3 options, loading, data, error rather than network error which is not needed.

Last thing I noticed is there was no changes to MediaDetail UI, Meaning most likely there is no way for his loadMedia func to be called, so there would be no media loaded at all. So thats the main thing I focused on in the review.

### Comments I Left
DefaultMediaRepository.kt Line 69:
*This might return fake data since it's getting it from the FakeMediaRepository instead of the API*

Main comments
*
One major thing I saw is that you did not change your `MediaDetailScreen.kt` and without that, you are most likely not loading your data from the API.

I would recommend using 

``` kotlin
LaunchedEffect(mediaId) {
     viewModel.setMediaId(mediaId)
}
```

which calls the function on first load nad once mediaID is changed. Only other issue is that you may need to also add a line to get the data and a few more changes for the different UI states.
*
---

## One Thing I Understood More Deeply
I learned a few things this week that I'm pretty proud of,

1. LaunchedEffect,
Comming from a React background, its very simular to useEffect snippet which is called when one of
the dependenices is changed. Learning about this by searching online thru the Kotlin and Jetpack Compose Documentation

Another thing this week, is that I've finally hit the Flow, where I deeply understood the way to use modifiers and actually
get the idea or concept from my brain to the actual physical app. I think this will continue to happen as I grow more familular with Kotlin
especially having classes with multiple states, for Successs(return type) and Error(error message) which is something I really like


---

## One Thing I'm Still Confused About
One thing I'm still working on learning is testing, I did'nt realize the power of LogCat, logging with Log.i (info)
Log.w warning and the others, and most importantly break-points where the code stops at a specific point and allows you to view the data from that point, its a very useful tool figuring out why something is not working. I'm also still working on learning the way Retrofit handles HTTP requests, as I'm used to it being done manualy where you create the request programatically, but retrofit handles most of that with @'s.

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
