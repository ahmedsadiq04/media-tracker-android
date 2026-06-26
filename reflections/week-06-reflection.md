# Week 6 Reflection

**Name:** Sadiq Ahmed

**Date:** 6/25/2026

---

## Commits This Week

<!-- Paste a link to your commits for this week. The easiest way: go to your repo on GitHub,
     click "commits", and copy the URL after filtering by your name or branch. -->

**Link:** [Week 6 Pull-Request](https://github.com/ahmedsadiq04/media-tracker-android/pull/8)

---

## Code Review

<!-- Every week you leave a review on a pod mate's pull request. Fill in both parts below.
     Part 1 is the link — I will verify the review exists on GitHub.
     Part 2 is your written assessment — what you actually looked at and what you found. -->

**Reviewed:** Ilyas
**Link to my review:** https://github.com/Ilyas9805/media-tracker-android/pull/6

### What I Looked At
I skimmed most of the files but opted to skip most as its just seems to be cloned from your branch, what I did look at was the Search UI, the main file, as well as the networking where the HTTP request was and I made sure he had the @Serializable since thats something I forgot and was breaking my code for a while.

### What I Noticed
I noticed that Ilyas might've had the same issue I did where we both had to copy large amounts of your repo into our repo as we either fell behind and/or modified too much and did not follow your programming structure. I had to do this due to the ladder, for example my AuthViewModel was a single file, while your was split into Login/Register. Looking into the files associated with the Search Functionality, it looked very simular to what I had implemented so nothing eye-catching there.

### Comments I Left
Seems to be in the same position that I was in in which you migt've needed to update a lot of oyur files since you have a ton of files being added, could be another reason tho.
Another thing I've noticed is that  your `SearchViewModel.kt` is no longer apart of this repo, might've been deleted on accident, Overall great work, and looking in-detail to the files that we've changed, the Search screen, as well as your MediaAPI Service and relevent files required for searching, it looks great and very simular to my code - which means it most likely works.

---

## One Thing I Understood More Deeply
The API aspect of this is the easy part for me as I have expirence a ton in Server-Side Architecture, so Bearer tokens and API
tokens were super easy for me to pick up, and looking more into Kotlin and how they serialize json is pretty nice, once you get the hang of things, and just like Java, Kotlin has the same issue where its just hella imports and abstraction, which I personally do not like. Overall, I think this week was a bit faster than the other weeks, and hopefully, if I can find some time in my schedule, I plan on taking on the challange of making the entire UI in about a week.

---

## One Thing I'm Still Confused About
One thing that really confuses me about Kotlin and mainly Android studio is their state machine. I came from React,
so having states is pretty easy where you can define `const [password, setPassword] = useState("")` and the password is read-only,
which is just super easy to me, most likely because I already knew that, but in Kotlin, it just seems a ton of work defining a new class,
creating the state, and have it update in its own contained bubble, and yeah it keeps the code clean, but on the other hand its another layer of abstraction.

---

## Anything Else *(optional)*
This week I took some time to make sure my code was sync'd up with your code, after I did my changes, I noticed that a ton of my stuff was not the same as yours, and my pod-mates usually followed your direction and design, while I took the time to try and figure things out on my own, but becuase I tried it on my own, at the end of it, my code becomes more deviated, like me using a single auth model over twin, or having unique errors, and its nice because these are the things I like to add, but it also is not great because when I'm stuck, like this week, I have nothing to fall back on. In the future, I plan on still writing it my own, but trying to see first how you implemented things. 

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
