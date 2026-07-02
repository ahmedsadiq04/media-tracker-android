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
I skimmed most of the files but opted to skip most as it just seems to be cloned from your branch. What I did look at was the Search UI, the main file, as well as the networking where the HTTP request was, and I made sure he had the @Serializable since that's something I forgot and was breaking my code for a while. I also took a look at some of the files that should've been changed this week, mainly the `strings.xml`, `registerScreen.kt`, authModels, and others, and found some surprises and informed him about them.

### What I Noticed
I noticed that Ilyas might've had the same issue I did, where we both had to copy large amounts of your repo into our repo as we either fell behind and/or modified too much and did not follow your programming structure. I had to do this because of the latter. For example, my AuthViewModel was a single file, while yours was split into Login/Register. I also tried to take a look at the rest of his code, sifting through the bloated PR for the things that were actually changed, and I noticed that he had a message in `strings.xml` that said `Account Created, please sign in` but I beleave this is redundent as most apps automatically log you in after creating an account, especially this app where once you register an account, it acts like you logged in.

### Comments I Left
The main thing I noticed is that `SearchViewModel.kt` is no longer a part of this repo, might've been deleted by accident. This might cause a build error while trying to compile your code. Also, a lot of your PR seems to be major changes trying to sync up your code back to what the professor has, which kinda makes your PR bloated with a bunch of files being changed. I also noticed on `strings.xml` that you have a string for Account created which prompts the user to sign in; this might not be needed, as creating an account typically should automatically log in the user. Sometimes you have to authenticate with an email code, but for the vast majority, it should act as a login on success.

---

## One Thing I Understood More Deeply
The API aspect of this is the easy part for me as I have expirence a ton in Server-Side Architecture, so Bearer tokens and API
tokens were super easy for me to pick up, and looking more into Kotlin and how they serialize json is pretty nice, once you get the hang of things, and just like Java, Kotlin has the same issue where its just hella imports and abstraction, which I personally do not like. Overall, I think this week was a bit faster than the other weeks, and hopefully, if I can find some time in my schedule, I plan on taking on the challenge of making the entire UI in about a week.

---

## One Thing I'm Still Confused About
One thing that really confuses me about Kotlin and mainly Android Studio is their state machine. I came from React,
so having states is pretty easy where you can define `const [password, setPassword] = useState("")` and the password is read-only,
which is just super easy to me, most likely because I already knew that, but in Kotlin, it just seems a ton of work defining a new class,
creating the state, and having it update in its own contained bubble, and yeah, it keeps the code clean, but on the other hand it's another layer of abstraction.

---

## Anything Else *(optional)*
This week I took some time to make sure my code was sync'd up with your code, after I did my changes, I noticed that a ton of my stuff was not the same as yours, and my pod-mates usually followed your direction and design, while I took the time to try and figure things out on my own, but becuase I tried it on my own, at the end of it, my code becomes more deviated, like me using a single auth model over twin, or having unique errors, and its nice because these are the things I like to add, but it also is not great because when I'm stuck, like this week, I have nothing to fall back on. In the future, I plan on still writing it myself, but trying to see first how you implemented things. 

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
