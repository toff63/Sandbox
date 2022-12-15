PROMPT="%(?:%{$fg_bold[green]%}➜ :%{$fg_bold[red]%}➜ )"
PROMPT+='%{$fg[magenta]%}%* %{$fg_bold[green]%}%n:%{$fg_bold[blue]%}%~%{$reset_color%} $(git_prompt_info) '

ZSH_THEME_GIT_PROMPT_PREFIX="%{$fg_bold[blue]%}(%{$fg[red]%}"
ZSH_THEME_GIT_PROMPT_SUFFIX="%{$reset_color%} "
ZSH_THEME_GIT_PROMPT_DIRTY="%{$fg[blue]%}) %{$fg[yellow]%}✗"
ZSH_THEME_GIT_PROMPT_CLEAN="%{$fg[blue]%})"

function preexec() {
    timer=$(date +%s)
}

function precmd() {
    if [ $timer   ]; then
        now=$(date +%s)
        elapsed=$(($now-$timer))
        export RPROMPT="%F{cyan}${elapsed}s %{$reset_color%}"
        unset timer
    fi
}
