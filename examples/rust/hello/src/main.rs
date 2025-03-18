use std::thread;
use std::time::Duration;

fn hello() {
    thread::spawn(|| println!("Hello from thread 1"));
    thread::spawn(|| println!("Hello from thread 2"));
}

fn main() {
    println!("====== Hello Threads    =======");
    scoped_threads();
    println!("====== Good Bye Threads =======");

    // uncomment me! thread::sleep(Duration::from_secs(1));
}

fn no_scope_threads() {
    thread::spawn(|| println!("Hello from no scoped thread 1"));
    thread::spawn(|| println!("Hello from no scoped thread 2"));
}

fn scoped_threads() {
    thread::scope(|s| {
        s.spawn(|| println!("Hello from scoped thread 1"));
        s.spawn(|| println!("Hello from scoped thread 2"));
    })
}
