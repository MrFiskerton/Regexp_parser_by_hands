## Grammar for regular expressions

>0. `<RE>             → <RE> "|" <concatenation>`
>0. `<RE>             → <concatenation>`
>0. `<concatenation>  → <concatenation> <kleene>`
>0. `<concatenation>  → <kleene>`
>0. `<kleene>         → <kleene>"*"`
>0. `<kleene>         → "("<RE>")"`
>0. `<kleene>         → n`
>
>Where `n` from `[a-z]`.

There is a left recursion.

##After removing left recursion.


>0. `<RE>              → <concatenation> <RE_p>`
>0. `<RE_p>            → "|" <concatenation> <RE_p>`
>0. `<RE_p>            → ε`
>0. `<concatenation>   → <kleene> concatenation_p>`
>0. `<concatenation_p> → <kleene> <concatenation_p>`
>0. `<concatenation_p> → ε`
>0. `<kleene>          → n <kleene_p>`
>0. `<kleene>          → "("<RE>")" <kleene_p>`
>0. `<kleene_p>        → "*"<kleene_p>`
>0. `<kleene_p>        → ε`


  Non-termminals    | Destination
--------------------|--------------
`<RE>`              | Regular expression.
`<RE_p>`            | Applying union.
`<concatenation>`   | Regular expression without union.
`<concatenation_p>` | Applying concatenation.
`<kleene>`          | Regular expression without union & concatenation
`<kleene_p>`        | Applying kleene closure.


## FIRST and FOLLOW for non-terminals

   Non-termminals   |     FIRST    |       FOLLOW
--------------------|------------- |--------------------
 `<RE>`             | n, (         | $, )
 `<RE_p>`           | &#124;, ε    | $, )
 `<concatenation>`  | n, (         | &#124;, $, )
 `<concatenation_p>`| ε, n, (      | n, (, &#124;, $, )
 `<kleene>`         | n, (         | n, (, &#124;, $, )
 `<kleene_p>`       | *, ε         | n, (, &#124;, $, )

