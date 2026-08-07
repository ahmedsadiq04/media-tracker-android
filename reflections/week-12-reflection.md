# Week 12 Reflection — Bonus Feature Sprint (Week 2 of 2, Final)

*Second and last week of bonus feature work. Week 13 has no build time — this is the last chance to get your feature demo-ready before Week 14. This template replaces the standard weekly reflection, same as last week.*

**Name:** Sadiq Ahmed
**Date:** 8/6/2026
**My assigned bonus feature:** *Quotes*

---

## Commits This Week

**Link:** [PR](https://github.com/ahmedsadiq04/media-tracker-android/pull/13) [Branch](https://github.com/ahmedsadiq04/media-tracker-android/tree/week-12)

---

## Code Review

**Reviewed:** *Ilyas*
**Link to my review:** https://github.com/Ilyas9805/media-tracker-android/pull/12

### What I Looked At
I had to try and filter thru the changes for this week, although I did look thru all the files,
I mainly looked at commits: 10a8e2a, 473f6c3 since they were made on Aug 6th (today), and the file MediaDetailScreen.kt

### What I Noticed
Looking thru all the files, theres not really anything that stood out to me,
I just noted some small things that could be improved or changed depending on what hes looking for

I noted using Enums rather than strings for consistency especially when parsing JSON output from the server,

On line L349 I saw he changed his impl to remove the for loop that goes thru each Library Status and this was most likely
done because of the change from Enum type to a String, I noted it in the PR and wrote a comment for it, 

I also left a small note on R402 mentioning color change for items that have already been saved for UX reasons,
most sites don't keep the same color when a prompt changes, it changes the color as well.

The main comment I left was regarding bloated PRs in which this PR has changes from Jul 23, 9 commits, 22 files changes,
1,591 total line additions with 175 deletions. Because of this, the PR is just too bloated making the job of the reviewer more difficult as its hard
to pin-point which files / lines are changes for this week

### Comments I Left
*MediaDetailScreen.kt line R346*
``` markdown
This might've been a mistake, but the Library Status is normally an Enum and should be used as such, although if you are using a String instead of an Enum, this could be why, although its better to have it as an Enum for consistency.
```

*MediaDetailScreen.kt line L349*
``` markdown
You rewrote a better implementation that was not needed; you already had a great solution by using a for loop going through each one and setting the values. I don't see why the change was warranted
```

*MediaDetailScreen.kt line R402*
``` markdown
For UI/UX reasons the color should change if its already saved. Most apps do it this way so to make it clear to the user
```

*Review Comment*
``` markdown
Overall the changes for this are well done with clear comments, but PRs should be issolated to the feature that is being implemented with minimal changes. This PR has changes from 2 weeks ago which makes the PR bloated.
Try to keep branches sync'd with master/main to keep the PRs changed files reduced.
```

---

## Bonus Feature — Final Status

<!-- Be concrete and honest. This is your last chance to flag something before demos.
     What does your feature actually do, end to end, right now? What's polished vs. rough?
     Is there anything you know is broken or half-done that you want on my radar before Week 14? -->

**What works end-to-end, right now:**
Right now, Quotes are able to be posted, public or private and users are able to load them in the new
quotes window, filtering by quotes you made, and quotes from the public

**Tests written for this feature:** N/A

There are no tests written for this feature such as test files (quotes_test.kt), or otherwise;

 
**Known gaps or rough edges going into demos:**

Theres a few things that still need to be added, that if were presenting into the demo, can knock out a few of these in the coming weekend,

* Like Quotes - users can click but cannot like any quotes
* UnLike Quotes - same here, although the API does not expose or tell you which quotes you have liked and the only way to know is to try and like a quote and seeing if you get an error in return
* Edit Quotes - its partially implemented but missing the API call for it
* Constant Loading - right now it only loads the first 50 quotes, and theres no impl to continue loading as you scroll down
* Filter - I planned on having a search filter that allows you to search by book, quote, or otherwise thats not implemented

---

## One Thing I Understood More Deeply

<!-- Looking back across both bonus feature weeks — not just this week — what's the one thing that
     actually shifted in how you think about building a feature from scratch, start to finish? -->

For the past two weeks working on my own feature, I'm pretty proud of what I've gotten done, and although I'm still rusty / new to Kotlin and
Android Compose, I'm able to tone done on the way I write the UI, typically remembering / using the default components, but sometimes
searching online on specific components and how to use said components.

Really, its the UI and the way states are handled in Kotlin with private and public values, the public being watchable by the UI,
I wont lie that Kotlin and Java has a lot more variables and larger code files, especially with something like a simple loadQuote where you need
a viewmodel that holds the public watchable value and the private value that you set, and the UI needing to watch that public value,

Overall, it great but can sometimes feel like a lot for something so simple.

---

## One Thing I'm Still Confused About

Theres a lot of things in Kotlin that I really don't know because its still new to me,
like when I was reviewing Ilyas code I saw this: `if(response.isSuccessful) response.Body() ?: emptyList()` and I was confused on what
was `?:` even about, and after doing my own research, I've come to find out its pretty much a simplified version of the ternary operator:
`response.Body() != null ? response.Body() : emptyList()`, where its only for null operators which is a really nice feature of Kotlin.

There are most likely many more that I just don't really know about since I'm new to Kotlin still, but if I continue to
use Kotlin in the future, then perhaps I will become proficient in soon.

---

## Anything Else *(optional)*

<!-- Anything about the bonus feature sprint as a whole — the two-week format, being assigned a
     feature rather than choosing it, whatever's on your mind — is fair game here. -->

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Honest final-status report — what works end-to-end, what's rough, what's tested — plus a specific, genuine "Understood More Deeply" that reflects on the sprint as a whole, not just this week. | Present but vague, or only reports on this week rather than the feature's overall state. | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** same as every other week — I check the link before grading.
