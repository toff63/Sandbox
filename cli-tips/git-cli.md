Code review

git log --name-only   \
        --no-merges \
        --pretty=oneline \
        5d7c00ec981b46c2901def3fb459a3ffbecc4d2a..HEAD | sed '/ /d' | sort | uniq | \
        sed 's,^,https://github.com/toff63/Sandbox/blob/master/,'
