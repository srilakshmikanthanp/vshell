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
