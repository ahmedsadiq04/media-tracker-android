# Week 2 Reflection

**Name:** Sadiq Ahmed

**Date:** 5/30/2026

---

## Commits This Week

<!-- Paste a link to your commits for this week. The easiest way: go to your repo on GitHub,
     click "commits", and copy the URL after filtering by your name or branch. -->

**Link:** [https://github.com/ahmedsadiq04/media-tracker-android/tree/week-02](https://github.com/ahmedsadiq04/media-tracker-android/tree/week-02)

---

## Code Review

<!-- Every week you leave a review on a pod mate's pull request. Fill in both parts below.
     Part 1 is the link — I will verify the review exists on GitHub.
     Part 2 is your written assessment — what you actually looked at and what you found. -->

**Reviewed:** *N/A - Missed Class so I had to catch up,* <br/>
**Link to my review:** N/A

### What I Looked At
N/A

### What I Noticed
N/A

### Comments I Left
N/A

---

## One Thing I Understood More Deeply
While catching up, I was looking into how Kotlin handles functions and grew to learn that they dont have a parameter order, for example:

in cpp,
``` cpp
void myFunction(int A, int B, &FVector Out) {}
```

In this func, you always have to have it in that order,
You can have it default with sometihng like
``` cpp
void myFunction(int A = 0, int B = 0) {}
```

But in Kotlin, you can skip the order entirerly and place it how ever you choose.

For example

``` js
void GetInput(InputType Type, Value Type)
```

and then you just call

``` js
GetInput(
     Value = value,
     inputType = type,
);
```

---

## One Thing I'm Still Confused About
For this course, do we have to have our application be 1-to-1 to the application on the website, or could we have some fun with it? If its 1-to-1 I understand it would be easier to grade, but I feel that students should be able to show their artistic and creative freedom when creating this to make it their own.

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
