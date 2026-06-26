# Week 6 Reflection

**Name:** Sadiq Ahmed

**Date:** 6/25/2026

---

## Commits This Week

<!-- Paste a link to your commits for this week. The easiest way: go to your repo on GitHub,
     click "commits", and copy the URL after filtering by your name or branch. -->

**Link:** [Week 5 Pull-Request](https://github.com/ahmedsadiq04/media-tracker-android/pull/8)

---

## Code Review

<!-- Every week you leave a review on a pod mate's pull request. Fill in both parts below.
     Part 1 is the link — I will verify the review exists on GitHub.
     Part 2 is your written assessment — what you actually looked at and what you found. -->

**Reviewed:** Nicolas
**Link to my review:** https://github.com/NChyrklund/media-tracker-android/pull/6

### What I Looked At
I looked at all the files while doing the review, but here are the specific files that I've noticed something upon:
* ApiConstants.kt
* UserRepository.kt
* SessionRepository.kt
* UserApiService.kt
* strings.xml

### What I Noticed
I took a look at the mentioned files above and left some comments about them. Mainly noticed that Nicolas had a bunch of functionality that I have not added yet, mainly the session stuff as I had not gotten around to completing it yet. Another thing I noticed and marked was the API service in when the user creates an account, if there is an error such as 409 if theres a duplicate and the user cannot make another account on the same email or username, he added a string in the strings.xml for that specific error, although I'm not sure if the API has any dupe checking. I also made note the compairson from my Repo which has a the UserRepository almost identical to the default one when we forked compared to his which is a lot closer to the branch you currently have on GitHub.

### Comments I Left
Noticed the ClientID and ClientSecret at the top of /ApiConstants.kt which is a good idea for having all your constants in a single clean spot, the issue in my mind is that it may lead to redundancy where another might have to look at ApiConstants into BuildConfig which then leads to build.grade, a ReadMe might help wiht this but its something that may lead to this.

I also noticed that you split /UserRepository.kt, removing the createAccount and login functions from inside there to another class, not something to be worried or changed but differs from my program.

I like the email or username already taken reponse, although I'm not sure if the API will return that to us, something to keep an eye out for.

---

## One Thing I Understood More Deeply
This week I tried to learn how Kotlin did the API requests and figure out a bit about how Kotlin handles it.
I'm not used the way Kotlin tries to do everything for you with the @Body for the JSON body as well as @POST, @GET, and @PATCH for the other
HTTP methods. I'm used to Go and JS and how they handle it, especially with Go building the client and then having it be sent with the JSON serializer using types / structs which is quite simular to Kotlin.

---

## One Thing I'm Still Confused About
The way Kotlin handles Error handling is someting I'm still working on, with having generics and then using the `when` keyword
as a semi-switch statement when something happens, as an 'event' of some kind. Its kinda hard breaking old habbits as in Go, I'm used to having
every function return an error like this `data, err := CreateAccount(userData)`. Another thing I wished you covered a bit was the build.grade.kts, in which we had to update local.properties, although after a bit of trial and error, I think I had grasped it.

---

## Anything Else *(optional)*
One thing I notied in your API is that 'isFollowing' seems to be expected to return but when I actually used the API is was not there and caused my JSON serializer to crash as it expected it, Normalally, in a language like Go I could say `omitempty` if its not there which would then just drop it instead of searching for it, but I'm not sure if theres an equivalent in Kotlin - Just wanted to let you know just incase this is not intended.

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
