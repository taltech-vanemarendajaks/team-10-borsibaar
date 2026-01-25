# Team 10

## Members

| Name       | GitHub username |
|------------|------------------|
| Josten Laanemets | JostenLaanemets |
| Miikael Volkonski |  tokintmash |
| Reimo Namm |  reimonamm |
| Romet Arak |  rometarak |
| Varje Kivila | varje     |

## Workflow
- New issues are added to Backlog
- When an assignee is added to Issue, the issue is assigned state "Todo"
- When work is started on Issue, the issue is assigned state "In Progress"
- When Pull Request is created on Issue, the issue is assigned state "In Review"
- Pull Request requires two approvals before merge
- When Pull Request is accepted and merged, the issue is assigned state "Done"
- When Pull Request is rejected or changes are needed, the issue is assigned state "Todo"
- Work is done in one week iterations, constant flow
- Issues not Done during current iteration will be added to Next iteration

## Branch / commit conventions
- Direct push to main is forbidden
- A new branch is created per each Issue and named after the issue.
- Only lowercase letters, numbers and hypens are used in branch names ex: <123-short-title>
- Commit messages are written in the imperative, present tense


## Merging strategies
-We used merge commit and squash and merge strategies. 
-Merge commit was used to preserve full commit history when needed.
-Squash and merge was used to keep the main branch history clean by combining multiple commits into one. 
-We encountered merge conflicts when multiple team members edited the same files. 