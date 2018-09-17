# GitHubStarredRepos

This is a small application that extract and list the last 30 days most starred repositories on GitHub, using a GitHub REST API that provides necessary data.


For repositories listing, this app uses a 'RecyclerView'.

Each repository/row is represented by a 'CardView'.

The card view item incarnate the sample given in the "row-explained.png" file attached to this repository.


In order to fetch JSON data from the GitHub's REST API, this app uses "Volley" library to assure networking with GitHub's Web-Service.

As long as there is to much data sent by the WS, this app adopt the concept of "endless-scroll"/"pagination".


The GitHub API used is accessible from https://developer.github.com/v3/search/#search-repositories.
