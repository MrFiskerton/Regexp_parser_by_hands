## Grammar for regular expressions
Complete Grammar for Perl-style regular expressions.

>0. `<RE>            → <union>`
>0. `<RE>            → <simple-RE>`
>0. `<union>         → <RE> "|" <simple-RE>`
>0. `<simple-RE>     → <concatenation>`
>0. `<simple-RE>     → <basic-RE>`
>0. `<concatenation> → <simple-RE> <basic-RE>`
>0. `<basic-RE>      → <asterisk>`
>0. `<basic-RE>      → <plus>`
>0. `<basic-RE>      → <elementary-RE>`
>0. `<asterisk>      → <elementary-RE> "*"`
>0. `<plus>          → <elementary-RE> "+"`
>0. `<elementary-RE> → <group>`
>0. `<elementary-RE> → <any>`
>0. `<elementary-RE> → <eos>`
>0. `<elementary-RE> → <letter>`
>0. `<elementary-RE> → <set>`
>0. `<group>         → "(" <RE> ")"`
>0. `<any>           → "."`
>0. `<eos>           → "$"`
>0. `<letter>        → any non methacharacter`
>0. `<letter>        → "\" methacharacter`
>0. `<set>           → <positive-set>`
>0. `<set>           → <negative-set>`
>0. `<positive-set>  → "[" <set-items> "]"`
>0. `<negative-set>  → "[^" <set-items> "]"`
>0. `<set-items>     → <set-item>`
>0. `<set-items>     → <set-item> <set-items>`
>0. `<set-item>      → <range>`
>0. `<set-item>      → <letter>`
>0. `<range>         → <letter> "-" <letter>`

Let's simplify it for our purposes.

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

