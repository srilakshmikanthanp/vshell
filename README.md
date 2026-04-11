# vshell

**vshell** is a programmable, application-controlled shell designed for secure environments where full system shell access is not acceptable.

Instead of exposing a real OS shell, `vshell` lets you define exactly:

* what commands exist
* how they behave

> Think of it as a **virtual shell runtime embedded inside your application**.
> In vshell, a command is simply **application logic exposed through a shell interface**.

## 📦 Modules

* **core** → vshell grammar
* **parser** → parsing & AST
* **jvm** → evaluator
* **sample** → SSH server demo with basic commands

---

## ▶️ Running the Sample

The sample module exposes a controlled SSH shell:

* Runs on `127.0.0.1:2222`
* Username/password authentication
* Only registered commands are available

See [commands](sample/src/main/kotlin/com/srilakshmikanthanp/vshell/sample/commands) for an example command implementation.

```bash
./gradlew --no-daemon :sample:run
```

Then connect:

```bash
ssh -p 2222 "$(whoami)@127.0.0.1"
```

Default password:

```
password
```

---

## 💻 Example Session

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

---

## 🎯 When to Use vshell

Use `vshell` when you need:

* controlled SSH access
* safe production debugging
* restricted environments

---
