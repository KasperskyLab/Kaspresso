# Contribution guide

## Сode contribution workflow
1.	Find an open issue or create a new issue on issue tracker for the feature you want to contribute.
2.	Fork the project on GitHub. You need to create a feature-branch for your work on your fork, as this way you be able to submit a pull request.
3.	Make any necessary changes to the source code.
4.	Add tests that verify that your contribution works as expected and modify existing tests if required.
5.	Run all unit and UI tests and make sure all of them pass.
6.	Run code coverage to check if the lines of code you added are covered by unit tests.
7.	Once your feature is complete, prepare the commit with appropriate message and the issue number.
8.	Create a [pull request](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/about-pull-requests) and wait for the users to review. When you submit a pull request, please, agree to the terms of [CLA](https://github.com/KasperskyLab/Kaspresso/blob/master/CLA.md).
9.	Once everything is done, your pull request gets merged. Your feature will be available with the next release and your name will be added to [AUTHORS](https://github.com/KasperskyLab/Kaspresso/blob/master/AUTHORS.md).

## Branch naming
issue-***/detailed_description. Example: issue-306/fix-padding-breaks-autoscroll-interceptor

## Commits
The commit message should begin with: "Issue #***: ...". Example: "Issue #306: Fixed padding-breaks autoscroll interceptor".
