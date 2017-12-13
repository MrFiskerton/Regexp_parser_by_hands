## Complete Grammar for Perl-style regular expressions.

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
