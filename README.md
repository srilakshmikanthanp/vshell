# vshell

**vshell** is a programmable, application-controlled shell designed for secure environments where full system shell access is not acceptable.

Instead of exposing a real OS shell, `vshell` lets you define exactly:

* what commands exist
* how they behave

> Think of it as a **virtual shell runtime embedded inside your application**.
> In vshell, a command is simply **application logic exposed through a shell interface**.

## Modules

* **core** → vshell grammar
* **parser** → parsing & AST
* **jvm** → Vshell on top of JVM implementation
* **sample** → SSH server demo with basic commands

---

## Running the Sample

The sample module exposes a controlled SSH shell on `127.0.0.1:2222`

```bash
git clone https://github.com/srilakshmikanthanp/vshell.git
cd vshell
./gradlew --no-daemon :sample:run
```

Then connect to the SSH server using with default password `password`:

```bash
ssh -p 2222 "$(whoami)@127.0.0.1"
```

To understand about commands implementation, See some of the [commands](sample/src/main/kotlin/com/srilakshmikanthanp/vshell/sample/commands).

---

## Example Session

```text
user@127.0.0.1:/home/user> ls
core
jvm
sample

user@127.0.0.1:/home/user> cat 'Note.txt'
Note: quotes are required for arguments.

user@127.0.0.1:/home/user> exit
Exit: Bye!
```

## Vshell Grammar Cheat Sheet

```bash
# addition
echo %`${1 + 2}`;
# subtraction
echo %`${5 - 3}`;
# multiplication
echo %`${2 * 3}`;
# division
echo %`${10 / 2}`;
# modulo
echo %`${10 % 3}`;
# exponentiation
echo %`${2 ^ 3}`;
# unary minus
echo %`${-5}`;
# unary plus
echo %`${+5}`;

# parentheses for precedence
echo %`${(1 + 2) * 3}`;

# logical NOT
echo %`${!(true)}`;

# less than
echo %`${1 < 2}`;
# greater than
echo %`${3 > 2}`;
# less than or equal
echo %`${2 <= 2}`;
# greater than or equal
echo %`${3 >= 2}`;
# equality check
echo %`${1 == 1}`;
# inequality check
echo %`${1 != 2}`;

# logical AND
echo %`${true && false}`;
# logical OR
echo %`${true || false}`;

# integer literal
echo %`${123}`;
# hexadecimal literal
echo %`${0xFF}`;
# binary literal
echo %`${0b1010}`;
# octal literal
echo %`${0o77}`;
# numeric (floating point)
echo %`${1.23}`;
# boolean true
echo %`${true}`;
# boolean false
echo %`${false}`;
# double-quoted string
echo %`${"hello"}`;
# single-quoted string
echo %`${'world'}`;

# identifier
echo %`${USERNAME}`;

# AND execution
echo 'hello' && echo 'world';
# OR execution
echo 'hello' || echo 'world';
# pipe output
echo 'hello' | cat;

# expression substitution
echo %`Hello ${USERNAME}`;
# expression substitution
echo %`Sum: ${1 + 2}`;
# command substitution inside expression
echo %`${#(echo 'hello')}`;
```
