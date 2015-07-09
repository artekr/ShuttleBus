//
//  ViewController.swift
//  ShuttleBus
//
//  Created by yucun li on 2015-07-08.
//  Copyright (c) 2015 yucun li. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {

    @IBOutlet weak var tableView: UITableView!
    
    var schedule: NSMutableArray! = NSMutableArray()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.schedule.addObject("8:15")
        self.schedule.addObject("8:15")
        self.schedule.addObject("8:15")
        self.schedule.addObject("8:15")
        self.schedule.addObject("8:15")
        self.schedule.addObject("8:15")
        
        self.tableView.rowHeight = UITableViewAutomaticDimension
        self.tableView.estimatedRowHeight = 44.0
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.schedule.count
    }

    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        var cell: UITableViewCell = self.tableView.dequeueReusableCellWithIdentifier("cell") as UITableViewCell
        
        cell.textLabel?.text = self.schedule.objectAtIndex(indexPath.row) as? String
        
        return cell
    }

}

